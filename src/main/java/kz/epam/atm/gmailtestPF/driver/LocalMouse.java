package kz.epam.atm.gmailtestPF.driver;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.ExecuteMethod;

import java.util.Map;

public class LocalMouse implements Mouse {
    protected final ExecuteMethod executor;

    public LocalMouse(ExecuteMethod executor) {
        this.executor = executor;
    }

    protected Map<String, Object> paramsFromCoordinates(Coordinates where) {
        Map<String, Object> params = Maps.newHashMap();
        if (where != null) {
            String id = (String)where.getAuxiliary();
            params.put("element", id);
        }

        return params;
    }

    protected void moveIfNeeded(Coordinates where) {
        if (where != null) {
            this.mouseMove(where);
        }

    }

    public void click(Coordinates where) {
        this.moveIfNeeded(where);
        this.executor.execute("mouseClick", ImmutableMap.of("button", Integer.valueOf(0)));
    }

    public void contextClick(Coordinates where) {
        this.moveIfNeeded(where);
        this.executor.execute("mouseClick", ImmutableMap.of("button", Integer.valueOf(2)));
    }

    public void doubleClick(Coordinates where) {
        this.moveIfNeeded(where);
        this.executor.execute("mouseDoubleClick", ImmutableMap.of());
    }

    public void mouseDown(Coordinates where) {
        this.moveIfNeeded(where);
        this.executor.execute("mouseButtonDown", ImmutableMap.of());
    }

    public void mouseUp(Coordinates where) {
        this.moveIfNeeded(where);
        this.executor.execute("mouseButtonUp", ImmutableMap.of());
    }

    public void mouseMove(Coordinates where) {
        Map<String, Object> moveParams = this.paramsFromCoordinates(where);
        this.executor.execute("mouseMoveTo", moveParams);
    }

    public void mouseMove(Coordinates where, long xOffset, long yOffset) {
        Map<String, Object> moveParams = this.paramsFromCoordinates(where);
        moveParams.put("xoffset", xOffset);
        moveParams.put("yoffset", yOffset);
        this.executor.execute("mouseMoveTo", moveParams);
    }
}
