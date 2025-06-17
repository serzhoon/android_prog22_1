TARGET = ru.template.lab4_1

CONFIG += \
    auroraapp

PKGCONFIG += \

SOURCES += \
    src/main.cpp \

HEADERS += \

DISTFILES += \
    2.avi \
    audio.wav \
    qml/pages/SoundEffect.qml \
    qml/pages/Video.qml \
    rpm/ru.template.lab4_1.spec \

AURORAAPP_ICONS = 86x86 108x108 128x128 172x172

CONFIG += auroraapp_i18n

TRANSLATIONS += \
    translations/ru.template.lab4_1.ts \
    translations/ru.template.lab4_1-ru.ts \

.pro
QT += multimedia

.spec
Requires: qt5-qtmultimedia-plugin-mediaservice-gstmediacapture
Requires: qt5-qtmultimedia-plugin-mediaservice-gstmediaplayer
Requires: qt5-qtdeclarative-import-multimedia
Requires: qt5-qtmultimedia

