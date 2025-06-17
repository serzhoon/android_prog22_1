import QtQuick 2.0
import Sailfish.Silica 1.0

CoverBackground {
    objectName: "defaultCover"

    CoverTemplate {
        objectName: "applicationCover"
        primaryText: "App"
        secondaryText: qsTr("lab4_2_r")
        icon {
            source: Qt.resolvedUrl("../icons/lab4_2_r.svg")
            sourceSize { width: icon.width; height: icon.height }
        }
    }
}
