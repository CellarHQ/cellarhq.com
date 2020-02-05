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
import com.cellarhq.generated.tables.Databasechangeloglock;
import com.cellarhq.generated.tables.Drink;
import com.cellarhq.generated.tables.Glassware;
import com.cellarhq.generated.tables.Organization;
import com.cellarhq.generated.tables.PasswordChangeRequest;
import com.cellarhq.generated.tables.Photo;
import com.cellarhq.generated.tables.Style;

import javax.annotation.processing.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOUNT_EMAIL_PKEY = Indexes0.ACCOUNT_EMAIL_PKEY;
    public static final Index IDX_ACCOUNT_EMAIL_EMAIL = Indexes0.IDX_ACCOUNT_EMAIL_EMAIL;
    public static final Index UNQ_ACCOUNT_EMAIL_EMAIL = Indexes0.UNQ_ACCOUNT_EMAIL_EMAIL;
    public static final Index ACCOUNT_LINK_REQUEST_PKEY = Indexes0.ACCOUNT_LINK_REQUEST_PKEY;
    public static final Index ACCOUNT_OAUTH_CLIENT_USERNAME_KEY = Indexes0.ACCOUNT_OAUTH_CLIENT_USERNAME_KEY;
    public static final Index ACCOUNT_OAUTH_PKEY = Indexes0.ACCOUNT_OAUTH_PKEY;
    public static final Index ACTIVITY_PKEY = Indexes0.ACTIVITY_PKEY;
    public static final Index CATEGORY_PKEY = Indexes0.CATEGORY_PKEY;
    public static final Index IDX_CATEGORY_BREWERY_DB_ID = Indexes0.IDX_CATEGORY_BREWERY_DB_ID;
    public static final Index CELLAR_PKEY = Indexes0.CELLAR_PKEY;
    public static final Index CELLAR_SLUG_UNIQUE_CONSTRAINT = Indexes0.CELLAR_SLUG_UNIQUE_CONSTRAINT;
    public static final Index UNQ_CELLAR_SCREEN_NAME = Indexes0.UNQ_CELLAR_SCREEN_NAME;
    public static final Index CELLARED_DRINK_PKEY = Indexes0.CELLARED_DRINK_PKEY;
    public static final Index IDX_CELLARED_DRINK_DRINK_BY_DATE = Indexes0.IDX_CELLARED_DRINK_DRINK_BY_DATE;
    public static final Index DATABASECHANGELOGLOCK_PKEY = Indexes0.DATABASECHANGELOGLOCK_PKEY;
    public static final Index DRINK_PKEY = Indexes0.DRINK_PKEY;
    public static final Index IDX_DRINK_BREWERY_DB_ID = Indexes0.IDX_DRINK_BREWERY_DB_ID;
    public static final Index IDX_DRINK_NEEDS_MODERATION = Indexes0.IDX_DRINK_NEEDS_MODERATION;
    public static final Index GLASSWARE_PKEY = Indexes0.GLASSWARE_PKEY;
    public static final Index IDX_GLASSWARE_BREWERY_DB_ID = Indexes0.IDX_GLASSWARE_BREWERY_DB_ID;
    public static final Index ORGANIZATION_PKEY = Indexes0.ORGANIZATION_PKEY;
    public static final Index UNQ_ORGANIZATION_SLUG = Indexes0.UNQ_ORGANIZATION_SLUG;
    public static final Index PASSWORD_CHANGE_REQUEST_PKEY = Indexes0.PASSWORD_CHANGE_REQUEST_PKEY;
    public static final Index PHOTO_PKEY = Indexes0.PHOTO_PKEY;
    public static final Index UNQ_PHOTO_ORIGINAL_URL = Indexes0.UNQ_PHOTO_ORIGINAL_URL;
    public static final Index IDX_STYLE_BREWERY_DB_ID = Indexes0.IDX_STYLE_BREWERY_DB_ID;
    public static final Index STYLE_PKEY = Indexes0.STYLE_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ACCOUNT_EMAIL_PKEY = Internal.createIndex("account_email_pkey", AccountEmail.ACCOUNT_EMAIL, new OrderField[] { AccountEmail.ACCOUNT_EMAIL.ID }, true);
        public static Index IDX_ACCOUNT_EMAIL_EMAIL = Internal.createIndex("idx_account_email_email", AccountEmail.ACCOUNT_EMAIL, new OrderField[] { AccountEmail.ACCOUNT_EMAIL.EMAIL }, false);
        public static Index UNQ_ACCOUNT_EMAIL_EMAIL = Internal.createIndex("unq_account_email_email", AccountEmail.ACCOUNT_EMAIL, new OrderField[] { AccountEmail.ACCOUNT_EMAIL.EMAIL }, true);
        public static Index ACCOUNT_LINK_REQUEST_PKEY = Internal.createIndex("account_link_request_pkey", AccountLinkRequest.ACCOUNT_LINK_REQUEST, new OrderField[] { AccountLinkRequest.ACCOUNT_LINK_REQUEST.ID }, true);
        public static Index ACCOUNT_OAUTH_CLIENT_USERNAME_KEY = Internal.createIndex("account_oauth_client_username_key", AccountOauth.ACCOUNT_OAUTH, new OrderField[] { AccountOauth.ACCOUNT_OAUTH.CLIENT, AccountOauth.ACCOUNT_OAUTH.USERNAME }, true);
        public static Index ACCOUNT_OAUTH_PKEY = Internal.createIndex("account_oauth_pkey", AccountOauth.ACCOUNT_OAUTH, new OrderField[] { AccountOauth.ACCOUNT_OAUTH.ID }, true);
        public static Index ACTIVITY_PKEY = Internal.createIndex("activity_pkey", Activity.ACTIVITY, new OrderField[] { Activity.ACTIVITY.ID }, true);
        public static Index CATEGORY_PKEY = Internal.createIndex("category_pkey", Category.CATEGORY, new OrderField[] { Category.CATEGORY.ID }, true);
        public static Index IDX_CATEGORY_BREWERY_DB_ID = Internal.createIndex("idx_category_brewery_db_id", Category.CATEGORY, new OrderField[] { Category.CATEGORY.BREWERY_DB_ID }, false);
        public static Index CELLAR_PKEY = Internal.createIndex("cellar_pkey", Cellar.CELLAR, new OrderField[] { Cellar.CELLAR.ID }, true);
        public static Index CELLAR_SLUG_UNIQUE_CONSTRAINT = Internal.createIndex("cellar_slug_unique_constraint", Cellar.CELLAR, new OrderField[] { Cellar.CELLAR.SLUG }, true);
        public static Index UNQ_CELLAR_SCREEN_NAME = Internal.createIndex("unq_cellar_screen_name", Cellar.CELLAR, new OrderField[] { Cellar.CELLAR.SCREEN_NAME }, true);
        public static Index CELLARED_DRINK_PKEY = Internal.createIndex("cellared_drink_pkey", CellaredDrink.CELLARED_DRINK, new OrderField[] { CellaredDrink.CELLARED_DRINK.ID }, true);
        public static Index IDX_CELLARED_DRINK_DRINK_BY_DATE = Internal.createIndex("idx_cellared_drink_drink_by_date", CellaredDrink.CELLARED_DRINK, new OrderField[] { CellaredDrink.CELLARED_DRINK.DRINK_BY_DATE }, false);
        public static Index DATABASECHANGELOGLOCK_PKEY = Internal.createIndex("databasechangeloglock_pkey", Databasechangeloglock.DATABASECHANGELOGLOCK, new OrderField[] { Databasechangeloglock.DATABASECHANGELOGLOCK.ID }, true);
        public static Index DRINK_PKEY = Internal.createIndex("drink_pkey", Drink.DRINK, new OrderField[] { Drink.DRINK.ID }, true);
        public static Index IDX_DRINK_BREWERY_DB_ID = Internal.createIndex("idx_drink_brewery_db_id", Drink.DRINK, new OrderField[] { Drink.DRINK.BREWERY_DB_ID }, false);
        public static Index IDX_DRINK_NEEDS_MODERATION = Internal.createIndex("idx_drink_needs_moderation", Drink.DRINK, new OrderField[] { Drink.DRINK.NEEDS_MODERATION }, false);
        public static Index GLASSWARE_PKEY = Internal.createIndex("glassware_pkey", Glassware.GLASSWARE, new OrderField[] { Glassware.GLASSWARE.ID }, true);
        public static Index IDX_GLASSWARE_BREWERY_DB_ID = Internal.createIndex("idx_glassware_brewery_db_id", Glassware.GLASSWARE, new OrderField[] { Glassware.GLASSWARE.BREWERY_DB_ID }, false);
        public static Index ORGANIZATION_PKEY = Internal.createIndex("organization_pkey", Organization.ORGANIZATION, new OrderField[] { Organization.ORGANIZATION.ID }, true);
        public static Index UNQ_ORGANIZATION_SLUG = Internal.createIndex("unq_organization_slug", Organization.ORGANIZATION, new OrderField[] { Organization.ORGANIZATION.SLUG }, true);
        public static Index PASSWORD_CHANGE_REQUEST_PKEY = Internal.createIndex("password_change_request_pkey", PasswordChangeRequest.PASSWORD_CHANGE_REQUEST, new OrderField[] { PasswordChangeRequest.PASSWORD_CHANGE_REQUEST.ID }, true);
        public static Index PHOTO_PKEY = Internal.createIndex("photo_pkey", Photo.PHOTO, new OrderField[] { Photo.PHOTO.ID }, true);
        public static Index UNQ_PHOTO_ORIGINAL_URL = Internal.createIndex("unq_photo_original_url", Photo.PHOTO, new OrderField[] { Photo.PHOTO.ORIGINAL_URL }, true);
        public static Index IDX_STYLE_BREWERY_DB_ID = Internal.createIndex("idx_style_brewery_db_id", Style.STYLE, new OrderField[] { Style.STYLE.BREWERY_DB_ID }, false);
        public static Index STYLE_PKEY = Internal.createIndex("style_pkey", Style.STYLE, new OrderField[] { Style.STYLE.ID }, true);
    }
}
