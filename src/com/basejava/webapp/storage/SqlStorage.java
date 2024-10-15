package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    private final SqlHelper sqlhelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlhelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }


    @Override
    public void clear() {
        sqlhelper.execute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlhelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlhelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlhelper.execute("SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlhelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlhelper.execute("SELECT * FROM resume", ps -> {
            List<Resume> resumes = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid").replaceAll(" ", "");
                String fullName = rs.getString("full_name");
                Resume resume = new Resume(uuid, fullName);
                resumes.add(resume);
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlhelper.execute("SELECT COUNT(*) FROM resume", x -> {
            ResultSet rs = x.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}