import QtQuick 2.0
import Sailfish.Silica 1.0

CoverBackground {
    objectName: "defaultCover"

    CoverTemplate {
        objectName: "applicationCover"
        primaryText: "App"
        secondaryText: qsTr("lab3_3")
        icon {
            source: Qt.resolvedUrl("../icons/lab3_3.svg")
            sourceSize { width: icon.width; height: icon.height }
        }
    }
}
