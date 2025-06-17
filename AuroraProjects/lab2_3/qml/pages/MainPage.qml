import QtQuick 2.0
import Sailfish.Silica 1.0
Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All

    XmlListModel {
        id: model
        source: "https://cbr.ru/scripts/XML_val.asp?d=0"
        query: "/Valuta/Item"
        XmlRole { name: "name"; query: "Name/string()" }
        XmlRole { name: "nominal"; query: "Nominal/string()" }
        XmlRole { name: "parentCode"; query: "ParentCode/string()" }

        onStatusChanged: {
            if (status === XmlListModel.Ready) {
                console.log("Got", "Market Lib:");
            } else if (status === XmlListModel.Error) {
                console.error("Error loading Market Libs:", errorString());
            }
        }
    }

    ListView {
        anchors.fill: parent
        model: model
        spacing: 100
        delegate: Item {
            width: parent.width
            height: 60
            Column {
                Text { text: "Name: " + name }
                Text { text: "Nominal: " + nominal }
                Text { text: "ParentCode: " + parentCode }
            }
        }
    }
}
