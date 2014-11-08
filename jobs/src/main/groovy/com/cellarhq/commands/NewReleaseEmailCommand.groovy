package com.cellarhq.commands

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.amazonaws.services.simpleemail.model.SendEmailResult
import com.cellarhq.support.AmazonSupport
import com.cellarhq.support.DatabaseSupport
import groovy.util.logging.Slf4j

import static com.cellarhq.generated.Tables.ACCOUNT_EMAIL

@Slf4j
class NewReleaseEmailCommand  implements NamedCommand, DatabaseSupport, AmazonSupport {

    boolean dryRun
    private  AmazonSimpleEmailServiceClient client

    void configure(String[] args) {
        CliBuilder cli = new CliBuilder(
            usage: 'chq cellarMerge [-d|--dryrun] [target_cellar_id] [source_cellar_id]',
            header: 'This command will merge the given "source" Cellar record and all of its associated data to' +
                'the "target" cellar. This command will use the target\'s Cellar metadata, and will attempt' +
                'to keep all associated accounts. In cases where the target and source Cellars share either' +
                'email or oauth credentials, the target cellar\'s accounts will take precedence and the' +
                'source accounts will be deleted.'
        )

        cli.d(longOpt: 'dryrun', required: false, 'Dry run; no actions will be taken')

        OptionAccessor opt = cli.parse(args)
        dryRun = (boolean) opt.getProperty('d')

        client = new AmazonSimpleEmailServiceClient(basicCredentials)
    }

    boolean run() {
        if (!dryRun) {
            println('###################################################')
            println('## THIS IS NOT A DRY RUN (starting in 5 seconds) ##')
            println('###################################################')
            sleep(5000)
        }

        List<Long> accountIds = create.select(ACCOUNT_EMAIL.ID)
            .from(ACCOUNT_EMAIL)
            .fetchInto(Long)

        Integer successCount = 0
        Integer errorCount = 0

        List<List<Long>> collateList = accountIds.collate(5)
        collateList.each { List<Long> emailAccountIds ->
            List<String> emails = create.select(ACCOUNT_EMAIL.EMAIL)
                .from(ACCOUNT_EMAIL)
                .where(ACCOUNT_EMAIL.ID.in(emailAccountIds)).fetchInto(String)

            if (sendEmailToBccList(emails)) {
                successCount++
                sleep(1000)
            } else {
                errorCount++
            }
        }

        println("Sent ${successCount} emails succesfully")
        println("Sent ${errorCount}  emails unsuccesfully")

        return true

    }

    private boolean sendEmailToBccList(List<String> emails) {



        try {
            sendEmail('team@cellarhq.com', emails, 'CellarHQ: Huge new release!', """
                                | Hey there!
                                |
                                | We wanted to tell you about a brand new version of CellarHQ that has just been
                                | released. You've probably noticed the stability issues over the last 6 months:
                                | in order to improve the reliability and make it easier for us to make changes in the
                                | future, we have rewritten the entire site in the last 3 months.
                                |
                                | If you'd like to learn more about what has changed, please check out the blog:
                                | http://blog.cellarhq.com
                                |
                                | In order to be as secure as possible, we did not migrate your passwords from version
                                | 1.0 to version 2.0 - this means you need to follow the password recovery process.
                                |
                                |
                                | http://www.cellarhq.com/forgot-password
                                |
                                | If you login with twitter, there is no need to go through this process.
                                |
                                | Thanks for being a loyal CellarHQ supporter, please let us know what you think of the
                                | improvements!
                                |
                                | If you experience any problems logging in, please email team@cellarhq.com.
                                |
                                | Cheers!
                                | Kyle and Rob
                            """.stripMargin())
            return true
        } catch (all) {
            println("Error while sending email. ${all.message}")
        }

        return false
    }

    void sendEmail(String from, List<String> bccEmailAddresses, String subject, String body) {
        SendEmailResult result

        if (!dryRun) {
            Destination destination = new Destination()
            destination.bccAddresses = bccEmailAddresses
            result= client.sendEmail(
                new SendEmailRequest(
                    source: from,
                    destination: destination,
                    message: new Message(
                        subject: new Content(subject),
                        body: new Body(new Content(body))
                    )
                )
            )
        }

        String messageId = result?.messageId ?: 'DRYRUN'
        println("Sent email via amazon from: ${from} subject: ${subject} to: ${bccEmailAddresses} messageId: ${messageId}")
    }
}
