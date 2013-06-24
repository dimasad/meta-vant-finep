require recipes-kernel/linux/linux.inc

DESCRIPTION = "Linux kernel for OMAP processors - Real-time preemption patch"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "overo"

PV = "3.4"
PR = "r0"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git;branch=v3.4-rt;protocol=git \
	   file://defconfig \
           file://0001-board-overo.c-added-spidev-support.patch \
           "
