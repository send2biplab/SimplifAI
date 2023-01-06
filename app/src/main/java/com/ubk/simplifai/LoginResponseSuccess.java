package com.ubk.simplifai;

public class LoginResponseSuccess {
    public String auth_token;
    public User user;

    public static class User {
        private String id, roles, firstName, lastName, email, status, emailVerified, mobile, mobileVerified, stripeAccount;
        private User_settings settings;
        private String dateOfBirth, gender, referCode;
        private User_address[] address;
        private User_profilePic profilePic;
        private User_bankAccount bankAccount;

        public User(String id, String roles, String firstName, String lastName, String email, String status, String emailVerified, String mobile, String mobileVerified, String stripeAccount, User_settings settings, String dateOfBirth, String gender, String referCode, User_address[] address, User_profilePic profilePic, User_bankAccount bankAccount) {
            this.id = id;
            this.roles = roles;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.status = status;
            this.emailVerified = emailVerified;
            this.mobile = mobile;
            this.mobileVerified = mobileVerified;
            this.stripeAccount = stripeAccount;
            this.settings = settings;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.referCode = referCode;
            this.address = address;
            this.profilePic = profilePic;
            this.bankAccount = bankAccount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(String emailVerified) {
            this.emailVerified = emailVerified;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobileVerified() {
            return mobileVerified;
        }

        public void setMobileVerified(String mobileVerified) {
            this.mobileVerified = mobileVerified;
        }

        public String getStripeAccount() {
            return stripeAccount;
        }

        public void setStripeAccount(String stripeAccount) {
            this.stripeAccount = stripeAccount;
        }

        public User_settings getSettings() {
            return settings;
        }

        public void setSettings(User_settings settings) {
            this.settings = settings;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getReferCode() {
            return referCode;
        }

        public void setReferCode(String referCode) {
            this.referCode = referCode;
        }

        public User_address[] getAddress() {
            return address;
        }

        public void setAddress(User_address[] address) {
            this.address = address;
        }

        public User_profilePic getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(User_profilePic profilePic) {
            this.profilePic = profilePic;
        }

        public User_bankAccount getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(User_bankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public static class User_settings {

        }

        public static class User_profilePic {

        }

        public static class User_bankAccount {

        }
    }
}
