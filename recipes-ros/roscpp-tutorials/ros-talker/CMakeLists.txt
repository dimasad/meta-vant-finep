cmake_minimum_required(VERSION 2.8.3)
project(beginner_tutorials)

find_library (ROSCPP_LIB NAMES roscpp)
find_library (CPPCOMMON_LIB NAMES cpp_common)

## Build talker
add_executable(talker talker.cpp)
target_link_libraries(
  talker roscpp cpp_common pthread boost_signals-mt boost_filesystem-mt boost_system-mt 
  roscpp_serialization rostime boost_date_time-mt boost_thread-mt rosconsole 
  boost_regex-mt log4cxx xmlrpcpp)

install (TARGETS talker DESTINATION bin)