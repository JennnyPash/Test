package com.jenny.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JennyPash on 1/11/2017.
 */

public class Database extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "myHome.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Project, Integer> projectsDao;
    private RuntimeExceptionDao<Room, Integer> roomsDao;
    private RuntimeExceptionDao<Subject, Integer> subjectsDao;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.projectsDao = getRuntimeExceptionDao(Project.class);
        this.roomsDao = getRuntimeExceptionDao(Room.class);
        this.subjectsDao = getRuntimeExceptionDao(Subject.class);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Project.class);
            TableUtils.createTable(connectionSource, Room.class);
            TableUtils.createTable(connectionSource, Subject.class);
        } catch (SQLException e) {}
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
    }

    /* common methods */
    public int create(Entity entity) {
        if (entity.getClass() == Project.class) {
            return this.projectsDao.create((Project)entity);
        }

        if (entity.getClass() == Room.class) {
            return this.roomsDao.create((Room)entity);
        }

        if (entity.getClass() == Subject.class) {
            return this.subjectsDao.create((Subject)entity);
        }

        throw new InvalidParameterException();
    }

    public int update(Entity entity) {
        if (entity.getClass() == Project.class) {
            return this.projectsDao.update((Project)entity);
        }

        if (entity.getClass() == Room.class) {
            return this.roomsDao.update((Room)entity);
        }

        if (entity.getClass() == Subject.class) {
            return this.subjectsDao.update((Subject)entity);
        }

        throw new InvalidParameterException();
    }

    public int delete(Entity entity) {
        if (entity.getClass() == Project.class) {
            return this.projectsDao.delete((Project)entity);
        }

        if (entity.getClass() == Room.class) {
            return this.roomsDao.delete((Room)entity);
        }

        if (entity.getClass() == Subject.class) {
            return this.subjectsDao.delete((Subject)entity);
        }

        throw new InvalidParameterException();
    }

    /* PROJECTS */
    public Project getProject(int id) {
        return this.projectsDao.queryForId(id);
    }

    public List<Project> getAllProjects() {
        return this.projectsDao.queryForAll();
    }

    /* ROOMS */
    public Room getRoom(int id) {
        return this.roomsDao.queryForId(id);
    }

    public List<Room> getRoomsByProject(int projectId) {
        return this.roomsDao.queryForEq("project_id", projectId);
    }

    public List<Room> getAllRooms() {
        return this.roomsDao.queryForAll();
    }

    /* SUBJECTS */
    public Subject getSubject(int id) {
        return this.subjectsDao.queryForId(id);
    }

    public List<Subject> getAllSubjects() {
        return this.subjectsDao.queryForAll();
    }

    public List<Subject> getSubjectsByRoom(int roomId) {
        return this.subjectsDao.queryForEq("room_id", roomId);
    }
}
