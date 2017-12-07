package com.example.kazu.recordmanager;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by kazu on 2017/12/06.
 */

public class Subjects extends RealmObject {
    public RealmList<SubjectDetail> getSubjectDetails() {
        return subjectDetails;
    }

    public void setSubjectDetails(RealmList<SubjectDetail> subjectDetails) {
        this.subjectDetails = subjectDetails;
    }

    private RealmList<SubjectDetail> subjectDetails;
}
