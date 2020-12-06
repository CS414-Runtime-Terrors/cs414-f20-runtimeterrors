package com.csc14.runtimeterrors.game;

import com.csc14.runtimeterrors.game.BoardAssets.MatchScreen;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.csc14.runtimeterrors.game.OmegaChess;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        MatchScreen table = new MatchScreen();
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new OmegaChess(false, table), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}