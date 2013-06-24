DESCRIPTION = "ROS talker demo."
SECTION = "examples" 
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=789b32fb343e6b1dedef7a512f0164cc"
PR = "r0"

DEPENDS = "roscpp"

S = "${WORKDIR}"
SRC_URI = "file://talker.cpp \
	   file://CMakeLists.txt \
           file://LICENSE"

inherit cmake
