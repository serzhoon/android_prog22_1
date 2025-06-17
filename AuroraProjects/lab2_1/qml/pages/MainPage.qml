import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    Rectangle{
        id: rect
        width: 400
        height: 100
        color: "lightpink"

        MouseArea {
            id: mouseArea
            anchors.fill: parent
            onClicked: rect.state === 'clicked' ? rect.state = "" : rect.state = 'clicked';
        }

        states: [
            State {
                name: "clicked"

                PropertyChanges { target: rect; x: 300; y: 1000 }
            }
        ]
    }
}
