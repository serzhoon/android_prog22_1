import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
objectName: "main Page"
allowedOrientations: Orientation.All


TextInput {
    id:textinput
    anchors.centerIn: parent
    text:"Text"
}
Button{
    anchors.horizontalCenter: parent.horizontalCenter
    anchors.top: TextInput.bottom
    text:"Button"
    onClicked: {
        console.log(TextInput.text)
        console.log("Вывод в консоль при нажатии")
    }
}
}
