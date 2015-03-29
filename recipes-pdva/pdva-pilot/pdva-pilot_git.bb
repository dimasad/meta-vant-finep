DEFAULT_PREFERENCE = "-1"

DESCRIPTION = "Autopilot of UFMG's PDVA group"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
DEPENDS = "glib-2.0 libconfig"
PR = "r0"
PV = "0.0+git${SRCREV}"

SRC_URI = "git://github.com/dimasad/pdva-pilot.git;branch=master;protocol=git;destsuffix=pdva-pilot_src\
           https://github.com/mavlink/mavlink/archive/1.0.11.zip;subdir=mavlink_src;basepath=mavlink-1.0.11;name=mavlink\
           file://add-pdvapilot-message-generation-CMakeLists.patch;patchdir=${WORKDIR}/mavlink_src/mavlink-1.0.11"
SRC_URI[mavlink.md5sum] = "c41674de6259dd09d3fa47f3c64d4494"
SRC_URI[mavlink.sha256sum] = "09f80065021072dad96fd186f06e5d5fce917fbd6459783b03e09502b775e3c0"
SRCREV =  "master"

OECMAKE_SOURCEPATH = "${WORKDIR}/pdva-pilot_src"

inherit cmake pkgconfig pythonnative

do_patch_prepend() {
    bb.build.exec_func('do_copy_pdvapilot_msgdef', d)
}

do_copy_pdvapilot_msgdef() {
    cp ${WORKDIR}/pdva-pilot_src/pdvapilot.xml ${WORKDIR}/mavlink_src/mavlink-1.0.11/message_definitions/v1.0
}

do_configure_append() {
    cd ${WORKDIR}/mavlink_src/mavlink-1.0.11
    mkdir -p build
    cd build
    cmake ..
    
    ln -s ${WORKDIR}/mavlink_src/mavlink-1.0.11/build/C/include/mavlink ${WORKDIR}/pdva-pilot_src/mavlink
}

do_compile_prepend() {
    #Generate Mavlink messages
    cd ${WORKDIR}/mavlink_src/mavlink-1.0.11/build
    oe_runmake

    #Return to pdva-pilot build directory
    cd ${S}
}
