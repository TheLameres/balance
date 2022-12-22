#!/usr/bin/env bash

set -euo pipefail

[[ -d "${PWD}"/maven && ! -d "${HOME}"/.m2 ]] && ln -s "${PWD}"/maven "${HOME}"/.m2

if [[ "$#" -ne 1 ]]; then
  printf "%s [ major | minor | patch ]\n" "$(basename "$0")"
  exit 1
fi

case "$1" in
  major )
    printf "Bumping major\n"
    VERSION_PATTERN='${parsedVersion.nextMajorVersion}.0.0-SNAPSHOT'
    ;;
  minor )
    printf "Bumping minor\n"
    VERSION_PATTERN='${parsedVersion.majorVersion}.${parsedVersion.nextMinorVersion}.0-SNAPSHOT'
    ;;
  patch )
    printf "Bumping patch\n"
    VERSION_PATTERN='${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion}-SNAPSHOT'
    ;;
  * )
    printf "Unknown bump type %s\n" "$1"
    exit 1
    ;;
esac

./mvnw build-helper:parse-version versions:set -DgenerateBackupPoms=false -DnewVersion="${VERSION_PATTERN}"


