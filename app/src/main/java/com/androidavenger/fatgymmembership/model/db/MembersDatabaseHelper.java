package com.androidavenger.fatgymmembership.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MembersDatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "gymMember_db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "member_table";
    public static final String COLUMN_MEMBER_NAME = "member_name";
    public static final String COLUMN_MEMBER_LASTNAME = "member_lastname";
    public static final String COLUMN_MEMBER_ID = "member_id";
    public static final String COLUMN_MEMBER_PHONE = "member_phone";
    public static final String COLUMN_MEMBER_TYPE = "member_type";

    private Resources resources;


    public MembersDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        resources = context.getResources();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MEMBER_NAME + " TEXT, "
                + COLUMN_MEMBER_LASTNAME + " TEXT, "
                + COLUMN_MEMBER_TYPE + " TEXT, "
                + COLUMN_MEMBER_PHONE + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String update = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(update);
        onCreate(db);
        DATABASE_VERSION = newVersion;
    }
    public List<Member> getAllMembersFromDatabase(){

        Cursor memberCursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME, null);

        List<Member> members = new ArrayList<>();

        memberCursor.move(-1);

        while(memberCursor.moveToNext()){

            String memberName = memberCursor.getString(memberCursor.getColumnIndex(COLUMN_MEMBER_NAME));
            String memberLastname = memberCursor.getString(memberCursor.getColumnIndex(COLUMN_MEMBER_LASTNAME));
            String memberPhone = memberCursor.getString(memberCursor.getColumnIndex(COLUMN_MEMBER_PHONE));
            String memberType = memberCursor.getString(memberCursor.getColumnIndex(COLUMN_MEMBER_TYPE));
            int memberID = memberCursor.getInt(memberCursor.getColumnIndex(COLUMN_MEMBER_ID));

            Member member = new Member(memberName, memberLastname, memberPhone, memberType, memberID);
            members.add(member);
        }
        memberCursor.close();

        return members;
    }
    public long insertMemberIntoDatabase(Member member) {
        //String s = "INSER....";
        ContentValues memberValue = new ContentValues();

        memberValue.put(COLUMN_MEMBER_NAME, member.getMemberName());
        memberValue.put(COLUMN_MEMBER_LASTNAME, member.getMemberLastName());
        memberValue.put(COLUMN_MEMBER_PHONE, member.getMemberPhone());
        memberValue.put(COLUMN_MEMBER_TYPE, member.getMemberType());


        return getWritableDatabase().insert(TABLE_NAME, null, memberValue);
    }
    public void deleteMemberFromDatabase (Member member){

        String deleteSql = resources.getString(R.string.delete_command, TABLE_NAME, COLUMN_MEMBER_ID, member.getMemberId());
        getWritableDatabase().execSQL(deleteSql);
    }

}
