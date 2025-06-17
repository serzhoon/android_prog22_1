import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    Column {
      anchors.centerIn: parent
      spacing: 50
      Rectangle {
        id: rect
        width: 300
        height: 100
        color: "#00a86b"
      }
      Text {
          id: text
          text: qsTr("Text")
      }
      Image {
        id: img
        source: "img/img.jpg"
        width: 400
        height: 400
    }
}
}


