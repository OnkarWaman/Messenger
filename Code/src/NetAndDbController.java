
class NetAndDbController
{
    static void start() throws Exception {
        NetConnection.checkConnection();
        if (NetConnection.isConnected) {
            LoginPage.connectionStatusLabel.setText("Network connected");
            Thread.sleep(2000);
        }
        else {
            LoginPage.connectionStatusLabel.setText("Network Disconnected");
        }
        DbConection.connectToDatabase();
        while (true) {
            NetConnection.checkConnection();
            if (!NetConnection.isConnected) {
                DbConection.con = null;
                LoginPage.connectionStatusLabel.setText("Network Disconnected");
            }
            if (NetConnection.isConnected && DbConection.con == null) {
                LoginPage.connectionStatusLabel.setText("Network connected");
                Thread.sleep(2000);
                DbConection.connectToDatabase();
            }
            if (NetConnection.isConnected && DbConection.con != null) {
                LoginPage.connectionStatusLabel.setText(null);
            }
            Thread.sleep(2000);
        }
    }
}