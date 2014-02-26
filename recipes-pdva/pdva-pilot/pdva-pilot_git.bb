DEFAULT_PREFERENCE = "-1"

DESCRIPTION = "Autopilot of UFMG's PDVA group"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
DEPENDS = "glib-2.0 libconfig"
PR = "r0"
PV = "0.0+git${SRCREV}"

SRC_URI = "git://github.com/dimasad/pdva-pilot.git;branch=master;protocol=git;destsuffix=pdva-pilot_src\
           git://github.com/mavlink/mavlink.git;tag=1.0.11;protocol=git;destsuffix=mavlink_src\
           file://add-pdvapilot-message-generation-CMakeLists.patch;patchdir=${WORKDIR}/mavlink_src"
SRCREV =  "master"

OECMAKE_SOURCEPATH = "${WORKDIR}/pdva-pilot_src"

inherit cmake pkgconfig pythonnative

do_patch_prepend() {
    bb.build.exec_func('do_copy_pdvapilot_msgdef', d)
}

do_copy_pdvapilot_msgdef() {
    cp ${WORKDIR}/pdva-pilot_src/pdvapilot.xml ${WORKDIR}/mavlink_src/message_definitions/v1.0
}

do_configure_append() {
    cd ${WORKDIR}/mavlink_src
    mkdir -p build
    cd build
    cmake ..
}

do_compile_prepend() {
    cd ${WORKDIR}/mavlink_src/build
    make
    cp -r ${WORKDIR}/mavlink_src/build/C/include/mavlink ${WORKDIR}/pdva-pilot_src/
}
