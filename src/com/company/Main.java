package com.company;
import java.util.*;
import java.lang.String;
public class Main {

    public static void main(String[] args) {
        class Bank_customer {
            static int id = 0;
            int no_of_transactions = 0;
            int balance;
            String name;
            String pass;

            Bank_customer(String tempname, String checkedpass) {
                this.name = tempname;
                this.id = ++id;
                this.balance = 1000;
                String encrypt = "";
                for (int i = 0; i < checkedpass.length(); i++) {
                    int t = (checkedpass.charAt(i) + 1);
                    System.out.println(t);
                    if (t == 123)
                        t = 97;
                    else if (t == 91)
                        t = 65;
                    else if (t == 57)
                        t = 48;

                    encrypt += (char)(t);
                }
                this.pass = encrypt;

            }

            public void show() {
                System.out.println(this.name + " " + this.pass + " " + this.balance + " " + this.id);
            }

        }

        List<Bank_customer> Bank = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.println("Enter 1 for adding customer details 2 for banking operations 3 for exit");
            int ch = s.nextInt();
            if (ch == 1) {
                System.out.println("Enter your name");
                String tempname = s.next();
                String checkedpass = "";
                System.out.println("Enter a password containing atleast 2 digits,2 capital and 2 small letters with minimal length of 6");
                String temp = s.next();
                int flag = 0;
                while (flag == 0) {
                    if (temp.length() < 6) {
                        System.out.println("Check the length of the password typed and retype the password");
                        temp = s.next();
                    } else {
                        int cap = 0, small = 0, num = 0;
                        for (int i = 0; i < temp.length(); i++) {
                            if (temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z')
                                cap++;
                            else if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z')
                                small++;
                            else if (temp.charAt(i) >= '0' && temp.charAt(i) <= '9')
                                num++;

                        }
                        if (cap >= 2 && small >= 2 && num >= 2) {
                            int cc = 0;
                            while (cc == 0) {
                                System.out.println("Retype the password");
                                String checker = s.next();
                                if (checker.equals(temp)) {
                                    checkedpass = temp;
                                    cc = 1;
                                }
                            }
                            flag = 1;
                        } else {
                            System.out.println("Recheck the password typed and type the password");
                            temp = s.next();

                        }
                    }
                }
                Bank_customer bc = new Bank_customer(tempname, checkedpass);
                Bank.add(bc);
            } else if (ch == 2) {
                System.out.println("Enter 1 for ATM withdrawal 2 for credit 3 for account transfer 4 Account balance");
                int choice = s.nextInt();
                if (choice == 1) {
                    int id;
                    System.out.println("Enter your id");
                    id = s.nextInt();
                    Bank_customer bc = Bank.get(id - 1);
                    int amount;
                    System.out.println("Enter the amount you want to withdraw");
                    amount = s.nextInt();
                    System.out.println("Enter your password");
                    String pss = s.next();
                    String decrypt = "";
                    for (int i = 0; i < pss.length(); i++) {
                        decrypt += (char) (pss.charAt(i)+1);
                    }

                    if (bc.pass.equals(decrypt)) {
                        if (amount > bc.balance)
                            System.out.println("Insufficient balance");
                        else {
                            bc.balance = bc.balance - amount;
                            if(amount>5000)
                                bc.balance=bc.balance-10;
                            bc.no_of_transactions++;
                            System.out.println(bc.balance);
                        }
                    } else
                        System.out.println("Sorry the password is incorrect");
                    if(bc.no_of_transactions>5)
                    {
                        System.out.println("User is requested to change the password");

                    }
                    if(bc.no_of_transactions>10)
                    {
                       bc.balance=bc.balance-10;

                    }
                }
                else if (choice == 2) {
                int id;
                System.out.println("Enter your id");
                id = s.nextInt();
                Bank_customer bc = Bank.get(id - 1);
                int amount;
                System.out.println("Enter the amount you want to deposit");
                    amount = s.nextInt();
                    System.out.println("Enter your password");
                    String pss = s.next();
                    String decrypt = "";
                    for (int i = 0; i < pss.length(); i++) {
                        decrypt += (char) (pss.charAt(i)+1);
                    }
                if(decrypt.equals(bc.pass)) {
                    bc.balance = bc.balance + amount;
                    if(amount>5000)
                        bc.balance=bc.balance-10;
                    bc.no_of_transactions++;
                    if(bc.no_of_transactions>10)
                    {
                        bc.balance=bc.balance-10;

                    }
                    if(bc.no_of_transactions>5)
                    {
                        System.out.println("User is requested to change the password");

                    }
                    System.out.println(bc.balance);
                }
                else
                    System.out.println("Sorry the password is incorrect");
            } else if (choice == 3) {
                int id;
                System.out.println("Enter your id");
                id = s.nextInt();
                Bank_customer bc = Bank.get(id - 1);
                int ide, amount;
                System.out.println("Enter the beneficiar id");
                ide = s.nextInt();
                System.out.println("Enter the amount you want to transfer");
                amount = s.nextInt();
                    System.out.println("Enter your password");
                    String pss = s.next();
                    String decrypt = "";
                    for (int i = 0; i < pss.length(); i++) {
                        decrypt += (char) (pss.charAt(i)+1);
                    }
                    if(decrypt.equals(bc.pass)) {
                        Bank_customer cc = Bank.get(ide - 1);
                        if (amount > 5000) {
                            cc.balance = cc.balance + amount;
                            bc.balance = bc.balance - amount - 10;
                        }
                        else
                        { cc.balance = cc.balance + amount;
                        bc.balance = bc.balance - amount;
                            System.out.println(bc.balance);
                            System.out.println(cc.balance);}
                        bc.no_of_transactions++;
                        if(bc.no_of_transactions>5)
                        {
                            System.out.println("User is requested to change the password");

                        }
                        if(bc.no_of_transactions>10) {
                            bc.balance = bc.balance - 10;

                        }

                    }
                    else
                        System.out.println("Sorry the password is incorrect");

            } else {
                int id;
                System.out.println("Enter your id");
                id = s.nextInt();
                    Bank_customer bc = Bank.get(id - 1);
                    System.out.println("Enter your password");
                    String pss = s.next();
                    String decrypt = "";
                    for (int i = 0; i < pss.length(); i++) {
                        int t = (pss.charAt(i)+1);
                        if(t==123)
                            t=96;
                        else if(t==90)
                            t=65;
                        else if(t==57)
                            t=48;
                        decrypt += (char) (pss.charAt(i)+1);
                    }
                    if(decrypt.equals(bc.pass))
                    {
                System.out.println(bc.id + " " + bc.name + " " + bc.balance +" "+bc.pass);}
                    else
                        System.out.println("Sorry the password is incorrect");

            }
        }
            else
                break;
        }

    }
}
