import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    Column {
        spacing: 50
        anchors.centerIn: parent

        Button {
            text: "Sound Effect"
            onClicked: pageStack.push(Qt.resolvedUrl("SoundEffect.qml"))
            backgroundColor: "#785394"
        }

        Button {
            text: "Video"
            onClicked: pageStack.push(Qt.resolvedUrl("Video.qml"))
            backgroundColor: "#785394"
        }
    }

    backgroundColor: "#513529"
}
