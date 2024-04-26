package io.codeforall.hackaton.Exception;

import io.codeforall.hackaton.errors.ErrorMessage;

public class KidNotFoundException extends HeyJudeException {

    public KidNotFoundException() {
        super(ErrorMessage.KID_NOT_FOUND);
    }
}

