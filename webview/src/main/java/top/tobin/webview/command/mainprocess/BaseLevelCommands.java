package top.tobin.webview.command.mainprocess;

import top.tobin.webview.command.base.Commands;
import top.tobin.webview.utils.WebConstants;

public class BaseLevelCommands extends Commands {

    public BaseLevelCommands() {
    }

    @Override
    protected int getCommandLevel() {
        return WebConstants.LEVEL_BASE;
    }
}
