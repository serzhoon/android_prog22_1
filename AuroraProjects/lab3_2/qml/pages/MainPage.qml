import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    Loader {
        id: rect
        anchors.centerIn: parent
        source: "RectComponent.qml"
    }
}
