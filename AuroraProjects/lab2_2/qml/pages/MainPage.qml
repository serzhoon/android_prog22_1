import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    Column {
        anchors.centerIn: parent
        spacing: 300

        Rectangle {
            id: rect1
            width: 200
            height: 100
            color: "lightpink"

            RotationAnimation on rotation {
                loops: Animation.Infinite
                from: 0
                to: 90
            }
        }

        Rectangle {
            id: rect2
            width: 200
            height: 100
            color: "lightyellow"

            RotationAnimation on rotation {
                loops: Animation.Infinite
                from: 0
                to: 180
            }
        }

        Rectangle {
            id: rect3
            width: 200
            height: 100
            color: "lightgreen"

            RotationAnimation on rotation {
                loops: Animation.Infinite
                from: 0
                to: 360
            }
        }
    }
}
