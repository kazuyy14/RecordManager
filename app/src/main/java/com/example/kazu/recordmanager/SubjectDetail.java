package com.example.kazu.recordmanager;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kazu on 2017/12/06.
 */

public class SubjectDetail extends RealmObject {

    private String name = "";
    private int unit = 0;
    private int status = 0;
    private int attendanceCount = 0;
    private String memo = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
