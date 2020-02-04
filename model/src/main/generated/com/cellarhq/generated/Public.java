/*
 * This file is generated by jOOQ.
 */
package com.cellarhq.generated;


import com.cellarhq.generated.tables.AccountEmail;
import com.cellarhq.generated.tables.AccountLinkRequest;
import com.cellarhq.generated.tables.AccountOauth;
import com.cellarhq.generated.tables.Activity;
import com.cellarhq.generated.tables.Category;
import com.cellarhq.generated.tables.Cellar;
import com.cellarhq.generated.tables.CellaredDrink;
import com.cellarhq.generated.tables.Databasechangelog;
import com.cellarhq.generated.tables.Databasechangeloglock;
import com.cellarhq.generated.tables.Drink;
import com.cellarhq.generated.tables.Glassware;
import com.cellarhq.generated.tables.Organization;
import com.cellarhq.generated.tables.PasswordChangeRequest;
import com.cellarhq.generated.tables.Photo;
import com.cellarhq.generated.tables.Style;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 841823104;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account_email</code>.
     */
    public final AccountEmail ACCOUNT_EMAIL = com.cellarhq.generated.tables.AccountEmail.ACCOUNT_EMAIL;

    /**
     * The table <code>public.account_link_request</code>.
     */
    public final AccountLinkRequest ACCOUNT_LINK_REQUEST = com.cellarhq.generated.tables.AccountLinkRequest.ACCOUNT_LINK_REQUEST;

    /**
     * The table <code>public.account_oauth</code>.
     */
    public final AccountOauth ACCOUNT_OAUTH = com.cellarhq.generated.tables.AccountOauth.ACCOUNT_OAUTH;

    /**
     * The table <code>public.activity</code>.
     */
    public final Activity ACTIVITY = com.cellarhq.generated.tables.Activity.ACTIVITY;

    /**
     * The table <code>public.category</code>.
     */
    public final Category CATEGORY = com.cellarhq.generated.tables.Category.CATEGORY;

    /**
     * The table <code>public.cellar</code>.
     */
    public final Cellar CELLAR = com.cellarhq.generated.tables.Cellar.CELLAR;

    /**
     * The table <code>public.cellared_drink</code>.
     */
    public final CellaredDrink CELLARED_DRINK = com.cellarhq.generated.tables.CellaredDrink.CELLARED_DRINK;

    /**
     * The table <code>public.databasechangelog</code>.
     */
    public final Databasechangelog DATABASECHANGELOG = com.cellarhq.generated.tables.Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    public final Databasechangeloglock DATABASECHANGELOGLOCK = com.cellarhq.generated.tables.Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>public.drink</code>.
     */
    public final Drink DRINK = com.cellarhq.generated.tables.Drink.DRINK;

    /**
     * The table <code>public.glassware</code>.
     */
    public final Glassware GLASSWARE = com.cellarhq.generated.tables.Glassware.GLASSWARE;

    /**
     * The table <code>public.organization</code>.
     */
    public final Organization ORGANIZATION = com.cellarhq.generated.tables.Organization.ORGANIZATION;

    /**
     * The table <code>public.password_change_request</code>.
     */
    public final PasswordChangeRequest PASSWORD_CHANGE_REQUEST = com.cellarhq.generated.tables.PasswordChangeRequest.PASSWORD_CHANGE_REQUEST;

    /**
     * The table <code>public.photo</code>.
     */
    public final Photo PHOTO = com.cellarhq.generated.tables.Photo.PHOTO;

    /**
     * The table <code>public.style</code>.
     */
    public final Style STYLE = com.cellarhq.generated.tables.Style.STYLE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ACCOUNT_EMAIL_ID_SEQ,
            Sequences.ACCOUNT_OAUTH_ID_SEQ,
            Sequences.ACTIVITY_ID_SEQ,
            Sequences.CATEGORY_ID_SEQ,
            Sequences.CELLARED_DRINK_ID_SEQ,
            Sequences.CELLAR_ID_SEQ,
            Sequences.DRINK_ID_SEQ,
            Sequences.GLASSWARE_ID_SEQ,
            Sequences.ORGANIZATION_ID_SEQ,
            Sequences.PHOTO_ID_SEQ,
            Sequences.STYLE_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            AccountEmail.ACCOUNT_EMAIL,
            AccountLinkRequest.ACCOUNT_LINK_REQUEST,
            AccountOauth.ACCOUNT_OAUTH,
            Activity.ACTIVITY,
            Category.CATEGORY,
            Cellar.CELLAR,
            CellaredDrink.CELLARED_DRINK,
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Drink.DRINK,
            Glassware.GLASSWARE,
            Organization.ORGANIZATION,
            PasswordChangeRequest.PASSWORD_CHANGE_REQUEST,
            Photo.PHOTO,
            Style.STYLE);
    }
}