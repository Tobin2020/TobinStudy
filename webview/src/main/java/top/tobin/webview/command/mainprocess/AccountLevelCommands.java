package top.tobin.webview.command.mainprocess;

import top.tobin.webview.command.base.Commands;
import top.tobin.webview.utils.WebConstants;

public class AccountLevelCommands extends Commands {

    public AccountLevelCommands() {
    }

    @Override
    protected int getCommandLevel() {
        return WebConstants.LEVEL_ACCOUNT;
    }

}
