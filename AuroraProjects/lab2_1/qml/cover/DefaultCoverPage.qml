import QtQuick 2.0
import Sailfish.Silica 1.0

CoverBackground {
    objectName: "defaultCover"

    CoverTemplate {
        objectName: "applicationCover"
        primaryText: "App"
        secondaryText: qsTr("lab2_1")
        icon {
            source: Qt.resolvedUrl("../icons/lab2_1.svg")
            sourceSize { width: icon.width; height: icon.height }
        }
    }
}
