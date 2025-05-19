# !/bin/bash
set -e

function print_error() {
    echo -e "\e[31mERROR: ${1}\e[m"
}

function print_info() {
    echo -e "\e[36mINFO: ${1}\e[m"
}

if [ -z "${PUBLISH_DIR}" ]; then
    echo "【致命错误】：workflows尚未设置 PUBLISH_DIR"
    exit 1
fi

if [ -d "$(pwd)${PUBLISH_DIR}" ]; then
    echo "【致命错误】：PUBLISH_DIR 尚未生成"
    exit 1
fi

if [ -z "${PUBLISH_BRANCH}" ]; then
    print_error "【致命错误】：没有发现 PUBLISH_BRANCH"
    exit 1
fi

cd "$(pwd)/site/${PUBLISH_DIR}" # ./_site

# 设置CNAME
if [ -n "${CNAME}" ]; then 
    echo "${CNAME}" > CNAME
fi

# 配置ssh
if [ -n "${DEPLOY_ACCESS_TOKEN}" ]; then
    print_info "设置 DEPLOY ACCES TOKEN"

    SSH_DIR="${HOME}/.ssh"
    mkdir "${SSH_DIR}"
    ssh-keyscan -t rsa github.com >"${SSH_DIR}/known_hosts"
    echo "${DEPLOY_ACCESS_TOKEN}" >"${SSH_DIR}/id_rsa"
    chmod 400 "${SSH_DIR}/id_rsa"
    cat "${SSH_DIR}/id_rsa"
    GIT_REPOSITORY_URL="git@github.com:${GITHUB_REPOSITORY}.git"
fi

# 配置git
git init
git checkout --orphan "${PUBLISH_BRANCH}" # 积累无数次commit，不算分支

git config user.name "${GITHUB_ACTOR}"
git config user.email "${GITHUB_ACTOR}@users.noreply.github.com"

git remote rm origin || true
git remote add origin "${GIT_REPOSITORY_URL}"

# 更改时区
cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

pushlist_time="$(date '+%Y-%m-%d %H:%M:%S')"

# git提交
git add .
git commit -m "【部署】：${pushlist_time}"

git push origin -f "${PUBLISH_BRANCH}"

print_info "${GITHUB_SHA} 部署成功：${pushlist_time}"