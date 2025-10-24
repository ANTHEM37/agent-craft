#!/bin/bash

# =============================================
# Agent Craft Backend Docker 构建脚本
# =============================================

set -e

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 配置变量
IMAGE_NAME="agent-craft/backend"
IMAGE_TAG="latest"
CONTAINER_NAME="agent-craft-backend"
DOCKERFILE_PATH="./Dockerfile"

# 函数：打印带颜色的消息
print_message() {
    local color=$1
    local message=$2
    echo -e "${color}[$(date '+%Y-%m-%d %H:%M:%S')] ${message}${NC}"
}

# 函数：检查命令是否存在
check_command() {
    if ! command -v $1 &> /dev/null; then
        print_message $RED "错误: $1 命令未找到，请先安装 $1"
        exit 1
    fi
}

# 函数：清理旧容器和镜像
cleanup() {
    print_message $YELLOW "清理旧容器和镜像..."
    
    # 停止并删除容器
    if docker ps -a --format 'table {{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        print_message $YELLOW "停止并删除容器: ${CONTAINER_NAME}"
        docker stop ${CONTAINER_NAME} || true
        docker rm ${CONTAINER_NAME} || true
    fi
    
    # 删除旧镜像
    if docker images --format 'table {{.Repository}}:{{.Tag}}' | grep -q "^${IMAGE_NAME}:${IMAGE_TAG}$"; then
        print_message $YELLOW "删除旧镜像: ${IMAGE_NAME}:${IMAGE_TAG}"
        docker rmi ${IMAGE_NAME}:${IMAGE_TAG} || true
    fi
}

# 函数：构建Maven项目
build_maven() {
    print_message $BLUE "开始Maven构建..."
    
    # 检查pom.xml是否存在
    if [ ! -f "pom.xml" ]; then
        print_message $RED "错误: 未找到pom.xml文件"
        exit 1
    fi
    
    # 执行Maven构建
    mvn clean package -DskipTests -Dmaven.test.skip=true
    
    if [ $? -eq 0 ]; then
        print_message $GREEN "Maven构建成功"
    else
        print_message $RED "Maven构建失败"
        exit 1
    fi
}

# 函数：构建Docker镜像
build_docker() {
    print_message $BLUE "开始构建Docker镜像..."
    
    # 检查Dockerfile是否存在
    if [ ! -f "${DOCKERFILE_PATH}" ]; then
        print_message $RED "错误: 未找到Dockerfile文件: ${DOCKERFILE_PATH}"
        exit 1
    fi
    
    # 构建Docker镜像
    docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -f ${DOCKERFILE_PATH} .
    
    if [ $? -eq 0 ]; then
        print_message $GREEN "Docker镜像构建成功: ${IMAGE_NAME}:${IMAGE_TAG}"
    else
        print_message $RED "Docker镜像构建失败"
        exit 1
    fi
}

# 函数：运行容器
run_container() {
    print_message $BLUE "启动Docker容器..."
    
    docker run -d \
        --name ${CONTAINER_NAME} \
        --network agent-craft-network \
        -p 8080:8080 \
        -e SPRING_PROFILES_ACTIVE=docker \
        -v agent-craft-logs:/app/logs \
        --restart unless-stopped \
        ${IMAGE_NAME}:${IMAGE_TAG}
    
    if [ $? -eq 0 ]; then
        print_message $GREEN "容器启动成功: ${CONTAINER_NAME}"
        print_message $GREEN "应用访问地址: http://localhost:8080"
    else
        print_message $RED "容器启动失败"
        exit 1
    fi
}

# 函数：显示帮助信息
show_help() {
    echo "Agent Craft Backend Docker 构建脚本"
    echo ""
    echo "用法: $0 [选项]"
    echo ""
    echo "选项:"
    echo "  build     构建Maven项目和Docker镜像"
    echo "  run       运行Docker容器"
    echo "  rebuild   清理、构建并运行"
    echo "  clean     清理旧容器和镜像"
    echo "  logs      查看容器日志"
    echo "  stop      停止容器"
    echo "  help      显示此帮助信息"
    echo ""
}

# 函数：查看日志
show_logs() {
    if docker ps --format 'table {{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        print_message $BLUE "显示容器日志..."
        docker logs -f ${CONTAINER_NAME}
    else
        print_message $RED "容器 ${CONTAINER_NAME} 未运行"
        exit 1
    fi
}

# 函数：停止容器
stop_container() {
    if docker ps --format 'table {{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        print_message $YELLOW "停止容器: ${CONTAINER_NAME}"
        docker stop ${CONTAINER_NAME}
        print_message $GREEN "容器已停止"
    else
        print_message $YELLOW "容器 ${CONTAINER_NAME} 未运行"
    fi
}

# 主函数
main() {
    # 检查必要的命令
    check_command docker
    check_command mvn
    
    case "${1:-help}" in
        "build")
            build_maven
            build_docker
            ;;
        "run")
            run_container
            ;;
        "rebuild")
            cleanup
            build_maven
            build_docker
            run_container
            ;;
        "clean")
            cleanup
            ;;
        "logs")
            show_logs
            ;;
        "stop")
            stop_container
            ;;
        "help"|*)
            show_help
            ;;
    esac
}

# 执行主函数
main "$@"