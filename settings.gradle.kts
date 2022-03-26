rootProject.name = "tower-ams-k"

include("domain")

include(
        "server:common",
        "server:job",
        "server:notification"
)