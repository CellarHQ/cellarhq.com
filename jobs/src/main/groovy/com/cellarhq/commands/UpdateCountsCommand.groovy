package com.cellarhq.commands

import com.cellarhq.support.DatabaseSupport
import com.cellarhq.generated.Keys
import org.jooq.impl.DSL

import static com.cellarhq.generated.Tables.*

class UpdateCountsCommand implements NamedCommand, DatabaseSupport {
    boolean dryRun

    void configure(String[] args) {
        CliBuilder cli = new CliBuilder(
            usage: 'chq updateCounts [-d|--dryrun]',
            header: 'This command will update the counts for beers and breweries.'
        )
        cli.d(longOpt: 'dryrun', required: false, 'Dry run; no actions will be taken')

        OptionAccessor opt = cli.parse(args)
        if (!opt) {
            throw new IllegalStateException('CLI opts could not be parsed')
        }

        dryRun = (boolean) opt.getProperty('d')
    }

    boolean run() {
        if (!dryRun) {
            println('###################################################')
            println('## THIS IS NOT A DRY RUN (starting in 5 seconds) ##')
            println('###################################################')
            sleep(5000)
        }

        destructive('Updating brewery counts') {
            List<Long> ids = create.select(ORGANIZATION.ID).from(ORGANIZATION).fetchInto(Long)

            ids.collate(100).sum { List<Long> batchOf100Ids ->
                create.batch(batchOf100Ids.collect { Long id ->
                    Integer totalBeers = create.fetchCount(
                        create.selectDistinct(DRINK.ID)
                            .from(DRINK)
                            .where(DRINK.ORGANIZATION_ID.eq(id))) ?: 0

                    Integer containedInCellars = create.fetchCount(
                        create.selectDistinct(CELLARED_DRINK.CELLAR_ID)
                            .from(CELLARED_DRINK)
                            .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                            .where(DRINK.ORGANIZATION_ID.eq(id))) ?: 0

                    Integer cellaredBeers = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
                        .from(CELLARED_DRINK)
                        .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                        .where(DRINK.ORGANIZATION_ID.eq(id)).fetchOneInto(Integer) ?: 0

                    create.update(ORGANIZATION)
                        .set(ORGANIZATION.TOTAL_BEERS, totalBeers)
                        .set(ORGANIZATION.CONTAINED_IN_CELLARS, containedInCellars)
                        .set(ORGANIZATION.CELLARED_BEERS, cellaredBeers)
                        .where(ORGANIZATION.ID.eq(id))
                }).execute().toList().sum()
            }
        }

        destructive('Updating beer counts') {
            List<Long> ids = create.select(DRINK.ID).from(DRINK).fetchInto(Long)

            ids.collate(100).sum { List<Long> batchOf100Ids ->
                create.batch(batchOf100Ids.collect { Long id ->
                    Integer containedInCellars = create.fetchCount(
                        create.selectDistinct(CELLARED_DRINK.CELLAR_ID)
                            .from(CELLARED_DRINK)
                            .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                            .where(DRINK.ID.eq(id))) ?: 0

                    Integer cellaredBeers = create.select(DSL.sum(CELLARED_DRINK.QUANTITY))
                        .from(CELLARED_DRINK)
                        .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                        .where(DRINK.ID.eq(id)).fetchOneInto(Integer) ?: 0

                    Integer forTrade = create.select(DSL.sum(CELLARED_DRINK.NUM_TRADEABLE))
                        .from(CELLARED_DRINK)
                        .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                        .where(DRINK.ID.eq(id)).fetchOneInto(Integer) ?: 0

                    create.update(DRINK)
                        .set(DRINK.CELLARED_BEERS, cellaredBeers)
                        .set(DRINK.CONTAINED_IN_CELLARS, containedInCellars)
                        .set(DRINK.TRADABLE_BEERS, forTrade)
                        .where(DRINK.ID.eq(id))
                }).execute().toList().sum()
            }
        }

        return true
    }

    void destructive(String description, Closure<Integer> operation) {
        println("## ${description}")
        if (!dryRun) {
            int rowsAffected = operation.call()
            println("  ${rowsAffected} rows affected")
        }
    }
}
