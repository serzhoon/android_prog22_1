import QtQuick 2.0
import Sailfish.Silica 1.0

CoverBackground {
    objectName: "defaultCover"

    CoverTemplate {
        objectName: "applicationCover"
        primaryText: "App"
        secondaryText: qsTr("lab4_3")
        icon {
            source: Qt.resolvedUrl("../icons/lab4_3.svg")
            sourceSize { width: icon.width; height: icon.height }
        }
    }
}
