import QtQuick 2.0
import Sailfish.Silica 1.0
Page {
    SilicaFlickable {
        anchors.fill: parent

        PullDownMenu {
            MenuLabel { text: "Информационная метка" }
            MenuItem {
                text: "Действие 1"
                onClicked: {
                    console.log("Действие 1")
                    slider.value = 20
                }
            }
            MenuItem {
                text: "Действие 2"
                onClicked: {
                    console.log("Действие 2")
                    slider.value = 40
                }
            }
            MenuItem {
                text: "Действие 3"
                onClicked: {
                    console.log("Действие 3")
                    slider.value = 60
                }
            }
        }
        Slider {
            id: slider
            width: parent.width
            maximumValue: 100
            minimumValue: 0
            value: 0
        }
    }
}
