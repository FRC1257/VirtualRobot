load("@bazel_tools//tools/cpp:cc_toolchain_config_lib.bzl", "feature", "flag_group", "flag_set", "tool_path")
load("@bazel_tools//tools/build_defs/cc:action_names.bzl", "ACTION_NAMES")

all_compile_actions = [
    ACTION_NAMES.c_compile,
    ACTION_NAMES.cpp_compile,
    ACTION_NAMES.linkstamp_compile,
    ACTION_NAMES.assemble,
    ACTION_NAMES.preprocess_assemble,
    ACTION_NAMES.cpp_header_parsing,
    ACTION_NAMES.cpp_module_compile,
    ACTION_NAMES.cpp_module_codegen,
    ACTION_NAMES.clif_match,
    ACTION_NAMES.lto_backend,
]

all_cpp_compile_actions = [
    ACTION_NAMES.cpp_compile,
    ACTION_NAMES.linkstamp_compile,
    ACTION_NAMES.cpp_header_parsing,
    ACTION_NAMES.cpp_module_compile,
    ACTION_NAMES.cpp_module_codegen,
    ACTION_NAMES.clif_match,
]

preprocessor_compile_actions = [
    ACTION_NAMES.c_compile,
    ACTION_NAMES.cpp_compile,
    ACTION_NAMES.linkstamp_compile,
    ACTION_NAMES.preprocess_assemble,
    ACTION_NAMES.cpp_header_parsing,
    ACTION_NAMES.cpp_module_compile,
    ACTION_NAMES.clif_match,
]

codegen_compile_actions = [
    ACTION_NAMES.c_compile,
    ACTION_NAMES.cpp_compile,
    ACTION_NAMES.linkstamp_compile,
    ACTION_NAMES.assemble,
    ACTION_NAMES.preprocess_assemble,
    ACTION_NAMES.cpp_module_codegen,
    ACTION_NAMES.lto_backend,
]

all_link_actions = [
    ACTION_NAMES.cpp_link_executable,
    ACTION_NAMES.cpp_link_dynamic_library,
    ACTION_NAMES.cpp_link_nodeps_dynamic_library,
]

lto_index_actions = [
    ACTION_NAMES.lto_index_for_executable,
    ACTION_NAMES.lto_index_for_dynamic_library,
    ACTION_NAMES.lto_index_for_nodeps_dynamic_library,
]

def _impl(ctx):
    tool_paths = [
        tool_path(
            name = "gcc",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-gcc",
        ),
        tool_path(
            name = "ld",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-ld",
        ),
        tool_path(
            name = "ar",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-ar",
        ),
        tool_path(
            name = "cpp",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-cpp",
        ),
        tool_path(
            name = "gcov",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-gcov",
        ),
        tool_path(
            name = "nm",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-nm",
        ),
        tool_path(
            name = "objdump",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-objdump",
        ),
        tool_path(
            name = "strip",
            path = "frc2021/roborio/bin/arm-frc2021-linux-gnueabi-strip",
        ),
    ]
    features = [
        feature(
            name = "roborio_toolchain_feature",
            enabled = True,
            flag_sets = [
                flag_set(
                    actions = [
                        ACTION_NAMES.c_compile,
                        ACTION_NAMES.cpp_compile,
                    ],
                    flag_groups = [
                        flag_group(
                            flags = [
                                "-no-canonical-prefixes",
                                "-std=c++17",
                            ],
                        ),
                    ],
                ),
                flag_set(
                    actions = [
                        ACTION_NAMES.cpp_link_executable,
                        ACTION_NAMES.cpp_link_dynamic_library,
                        ACTION_NAMES.cpp_link_nodeps_dynamic_library,
                        ACTION_NAMES.cpp_link_static_library,
                    ],
                    flag_groups = [
                        flag_group(
                            flags = [
                                "-lstdc++",
                                "-lpthread",
                            ],
                        ),
                    ],
                ),
            ],
        ),
    ]

    return cc_common.create_cc_toolchain_config_info(
        ctx = ctx,
        toolchain_identifier = "roborio_toolchain",
        host_system_name = "local",
        target_system_name = "arm-frc2021-linux-gnueabi",
        target_cpu = "armv7",
        target_libc = "glibc-2.24",
        cc_target_os = "linux",
        compiler = "gcc-7.3.0",
        abi_version = "gcc-7.3.0",
        abi_libc_version = "glibc-2.24",
        tool_paths = tool_paths,
        builtin_sysroot = "external/athena_toolchain_%s_files/frc2021/roborio/arm-frc2021-linux-gnueabi" % ctx.attr.toolchain_host,
        features = features,
    )

athena_cc_toolchain_config = rule(
    implementation = _impl,
    attrs = {
        "toolchain_host": attr.string(),
    },
    provides = [CcToolchainConfigInfo],
)