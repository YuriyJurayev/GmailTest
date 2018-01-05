package kz.epam.atm.gmailtestPF.driver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.ExecuteMethod;

public class LocalKeyboard implements Keyboard {
    protected final ExecuteMethod executor;

    public LocalKeyboard(ExecuteMethod executor) {
        this.executor = executor;
    }

    public void sendKeys(CharSequence... keysToSend) {
        if (keysToSend == null) {
            throw new IllegalArgumentException("Keys to send should be a not null CharSequence");
        } else {
            this.executor.execute("sendKeysToActiveElement", ImmutableMap.of("value", keysToSend));
        }
    }

    public void pressKey(CharSequence keyToPress) {
        CharSequence[] sequence = new CharSequence[]{keyToPress};
        this.executor.execute("sendKeysToActiveElement", ImmutableMap.of("value", sequence));
    }

    public void releaseKey(CharSequence keyToRelease) {
        CharSequence[] sequence = new CharSequence[]{keyToRelease};
        this.executor.execute("sendKeysToActiveElement", ImmutableMap.of("value", sequence));
    }
}