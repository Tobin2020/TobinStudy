package top.tobin.webview.command.webviewprocess;

import android.content.Context;
import android.util.Log;

import top.tobin.webview.command.base.Command;
import top.tobin.webview.command.base.ResultBack;
import top.tobin.webview.utils.WebConstants;

import java.util.Map;

public class WebViewProcessCommandsManager {
    private static WebViewProcessCommandsManager instance;
    private LocalCommands localCommands;

    private WebViewProcessCommandsManager() {
        localCommands = new LocalCommands();
    }

    public static WebViewProcessCommandsManager getInstance() {
        if (instance == null) {
            synchronized (WebViewProcessCommandsManager.class) {
                instance = new WebViewProcessCommandsManager();
            }
        }
        Log.d("WebViewPCM:", instance + "");
        return instance;
    }

    /**
     * 动态注册command
     * 应用场景：其他模块在使用WebView的时候，需要增加特定的command，动态加进来
     */
    public void registerCommand(int commandLevel, Command command) {
        switch (commandLevel) {
            case WebConstants.LEVEL_LOCAL:
                localCommands.registerCommand(command);
                break;
        }
    }

    /**
     * Commands handled by WebView itself, these command does not require aidl.
     */
    public void findAndExecLocalCommand(Context context, int level, String action, Map params, ResultBack resultBack) {
        if (localCommands.getCommands().get(action) != null) {
            localCommands.getCommands().get(action).exec(context, params, resultBack);
        }
    }

    public boolean checkHitLocalCommand(int level, String action) {
        return localCommands.getCommands().get(action) != null;
    }

}

