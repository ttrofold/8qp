package connector;

import javax.swing.*;

public class Connector {

    private static IConnectMethod connectMethod = new ConnectMethod();

    public static JComponent connect(String s) {
        return connectMethod.invoke(s);
    }

    public static void setConnectMethod(IConnectMethod connectMethod) {
        Connector.connectMethod = connectMethod;
    }
}
