import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    PageHeader { title: "Page " + pageStack.depth }
    Column {
        width: parent.width
        spacing: Theme.paddingLarge
        anchors.centerIn: parent

        Button {
            text: "Вперед"
            anchors.horizontalCenter: parent.horizontalCenter
            onClicked: pageStack.push(Qt.resolvedUrl("MainPage.qml"))
        }

        Button {
            text: "Назад"
            anchors.horizontalCenter: parent.horizontalCenter
            onClicked: pageStack.pop()
        }
    }
}
