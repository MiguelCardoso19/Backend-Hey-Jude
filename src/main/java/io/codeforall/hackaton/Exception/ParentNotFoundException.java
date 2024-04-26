package io.codeforall.hackaton.Exception;

import io.codeforall.hackaton.errors.ErrorMessage;

public class ParentNotFoundException extends HeyJudeException {

    public ParentNotFoundException() {
        super(ErrorMessage.PARENT_NOT_FOUND);
    }
}
