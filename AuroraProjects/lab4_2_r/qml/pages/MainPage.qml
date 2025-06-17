import QtQuick 2.0
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: OrientationAll
    backgroundColor: "#ffebcd"

    ListModel {
        id: jsonModel
    }

    ListView {
        anchors.fill: parent
        model: jsonModel
        spacing: 130

        delegate: Item {
            Row {
                spacing: 30
                x: 30

                Text {
                    text: "ID: " + model.id
                    color: "#ff5800"
                    font.pointSize: 30
                    font.Family: "monospace"
                }
                Text {
                    text: "Completed: " + model.completed
                    color: "#390000"
                    font.pointSize: 30
                    font.Family: "monospace"
                }
            }
        }
    }

    function processData(data) {
    for (var i = 0; i < data.length; i++) {
        jsonModel.append({
            "id": data[i].id,
            "completed": data[i].completed
        });
    }
}
function processData(data) {
    for (var i = 0; i < data.length; i++) {
        jsonModel.append({
            "id": data[i].id,
            "completed": data[i].completed
        });
    }
}

Component.onCompleted: {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                var response = JSON.parse(request.responseText);
                processData(response);
            } else {
                console.error("Failed to fetch JSON data", request.status, request.statusText);
            }
        }
    };
    request.open("GET", "https://jsonplaceholder.typicode.com/todos", true);
    request.send();
}
}
