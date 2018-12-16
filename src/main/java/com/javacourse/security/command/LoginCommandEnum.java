package com.javacourse.security.command;

import com.javacourse.shared.Command;

public enum LoginCommandEnum {
    SIGN_IN{
        {
            this.command = new SignInCommand();
        }
    },
    SIGN_UP{
        {
            this.command = new SignUpCommand();
        }
    },
    SHOW_SIGN_IN{
        {
            this.command = new ShowSignInCommand();
        }
    },
    SHOW_SIGN_UP{
        {
            this.command = new ShowSignUpCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    };

    Command command;
    public Command getCommand(){return command;}
}
