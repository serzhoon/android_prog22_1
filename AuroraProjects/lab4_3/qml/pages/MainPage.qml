import QtQuick 2.0
import Sailfish.Silica 1.0
import QtQuick.LocalStorage 2.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All
    backgroundColor: "black"

    property string surname: ""
    property string name: ""
    property string secondName: ""
    property var noteModel: ListModel {
        ListElement { note: "Киндрук Екатерина Александровна" }
        ListElement { note: "Дубовицкая Юлия Александровна" }
    }

    function addNote() {
        if (surname !== "" && name !== "" && secondName !== "") {
            noteModel.append({"note": surname + " " + name + " " + secondName})
        }
    }

    function deleteNote(index) {
        noteModel.remove(index)
    }

    function initializeDatabase() {
        var db = LocalStorage.openDatabaseSync("notesDB", "1.0", "Notes Database", 1000000)
        db.transaction(function(tx) {
            tx.executeSql('CREATE TABLE IF NOT EXISTS notes(note TEXT)')
            console.log("Таблица создана")
        })
    }

    Component.onCompleted: {
        initializeDatabase()
    }

    SilicaFlickable {
        anchors.fill: parent

        Column {
            id: column
            y: 30
            width: parent.width

            TextField {
                id: noteSurname
                placeholderText: "Фамилия"
                width: parent.width
                onTextChanged: surname = text
                EnterKey.onClicked: noteName.focus = true
                placeholderColor: noteSurname.highlighted ? "#423189" : Theme.darkSecondaryColor
            }

            TextField {
                id: noteName
                placeholderText: "Имя"
                onTextChanged: name = text
                EnterKey.onClicked: noteSecondName.focus = true
                placeholderColor: noteName.highlighted ? "#423189" : Theme.darkSecondaryColor
            }

            TextField {
                id: noteSecondName
                placeholderText: "Отчество"
                onTextChanged: secondName = text
                EnterKey.onClicked: focus = false
                placeholderColor: noteSecondName.highlighted ? "#423189" : Theme.darkSecondaryColor
            }

            Button {
                id: addButton
                text: "Добавить"
                anchors.horizontalCenter: parent.horizontalCenter
                color: "#423189"
                highlightBackgroundColor: "#b4a8e0"
                highlightColor: "#b69eff"
                onClicked: addNote()
            }
        }

        ListView {
            id: noteListView
            width: parent.width
            height: parent.height / 2
            model: noteModel
            anchors.top: column.bottom
            delegate: Item {
                width: parent.width
                height: Theme.itemSizeMedium
                Row {
                    x: 20
                    Label {
                        text: model.note
                        wrapMode: Text.Wrap
                        width: 600
                        anchors.verticalCenter: parent.verticalCenter
                        color: Theme.primaryColor
                        font.family: "Liberation Mono"
                        font.pixelSize: Theme.fontSizeSmall
                    }
                    IconButton {
                        id: btnDelete
                        icon.source: "delete.png"
                        onClicked: deleteNote(index)
                    }
                }
            }
        }
    }
}
