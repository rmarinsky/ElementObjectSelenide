package com.tempmail.mailutil;

/**
 * Created by Tester3 on 19.10.2016.
 */
public class HttpPostParameter {

    private String field, value;

    public HttpPostParameter(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HttpPostParameter [field=" + field + ", value=" + value + "]";
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof HttpPostParameter))
            return false;
        HttpPostParameter other = (HttpPostParameter) obj;
        if (field == null) {
            if (other.field != null)
                return false;
        } else if (!field.equals(other.field))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
