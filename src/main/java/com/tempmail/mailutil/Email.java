package com.tempmail.mailutil;

/**
 * Created by Tester3 on 19.10.2016.
 */
public class Email {

    protected double mailTimestamp;

    // mailID the hash of the message id to be used to delete messages
    // mail address id is the md5hash

    protected String mailID, mailAddressID, mailFrom, mailSubject, mailPreview, mailTextOnly, mailText, mailHtml;


    public double getMailTimestamp() {
        return mailTimestamp;
    }

    public void setMailTimestamp(double mailTimestamp) {
        this.mailTimestamp = mailTimestamp;
    }

    /**
     * @return Returns the unique identifier md5 hash. This hash is used in
     * deleting messages.
     */
    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    /**
     * @return returns the email address in md5 hash form.
     */
    public String getMailAddressID() {
        return mailAddressID;
    }

    public void setMailAddressID(String mailAddressID) {
        this.mailAddressID = mailAddressID;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailPreview() {
        return mailPreview;
    }

    public void setMailPreview(String mailPreview) {
        this.mailPreview = mailPreview;
    }

    public String getMailTextOnly() {
        return mailTextOnly;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mailAddressID == null) ? 0 : mailAddressID.hashCode());
        result = prime * result + ((mailFrom == null) ? 0 : mailFrom.hashCode());
        result = prime * result + ((mailHtml == null) ? 0 : mailHtml.hashCode());
        result = prime * result + ((mailID == null) ? 0 : mailID.hashCode());
        result = prime * result + ((mailPreview == null) ? 0 : mailPreview.hashCode());
        result = prime * result + ((mailSubject == null) ? 0 : mailSubject.hashCode());
        result = prime * result + ((mailText == null) ? 0 : mailText.hashCode());
        result = prime * result + ((mailTextOnly == null) ? 0 : mailTextOnly.hashCode());
        long temp;
        temp = Double.doubleToLongBits(mailTimestamp);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Email other = (Email) obj;
        if (mailAddressID == null) {
            if (other.mailAddressID != null)
                return false;
        } else if (!mailAddressID.equals(other.mailAddressID))
            return false;
        if (mailFrom == null) {
            if (other.mailFrom != null)
                return false;
        } else if (!mailFrom.equals(other.mailFrom))
            return false;
        if (mailHtml == null) {
            if (other.mailHtml != null)
                return false;
        } else if (!mailHtml.equals(other.mailHtml))
            return false;
        if (mailID == null) {
            if (other.mailID != null)
                return false;
        } else if (!mailID.equals(other.mailID))
            return false;
        if (mailPreview == null) {
            if (other.mailPreview != null)
                return false;
        } else if (!mailPreview.equals(other.mailPreview))
            return false;
        if (mailSubject == null) {
            if (other.mailSubject != null)
                return false;
        } else if (!mailSubject.equals(other.mailSubject))
            return false;
        if (mailText == null) {
            if (other.mailText != null)
                return false;
        } else if (!mailText.equals(other.mailText))
            return false;
        if (mailTextOnly == null) {
            if (other.mailTextOnly != null)
                return false;
        } else if (!mailTextOnly.equals(other.mailTextOnly))
            return false;
        if (Double.doubleToLongBits(mailTimestamp) != Double.doubleToLongBits(other.mailTimestamp))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Email [mailTimestamp=" + mailTimestamp + ", mailID=" + mailID + ", mailAddressID=" + mailAddressID
                + ", mailFrom=" + mailFrom + ", mailSubject=" + mailSubject + ", mailPreview=" + mailPreview
                + ", mailTextOnly=" + mailTextOnly + ", mailText=" + mailText + ", mailHtml=" + mailHtml + "]";
    }

    public void setMailTextOnly(String mailTextOnly) {
        this.mailTextOnly = mailTextOnly;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getMailHtml() {
        return mailHtml;
    }

    public void setMailHtml(String mailHtml) {
        this.mailHtml = mailHtml;
    }
}
