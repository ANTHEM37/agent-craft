# =============================================
# Agent Craft Backend Dockerfile
# 基于 OpenJDK 21 构建 Spring Boot 应用
# =============================================

# 使用多阶段构建优化镜像大小
FROM maven:3.9.6-openjdk-21-slim AS builder

# 设置工作目录
WORKDIR /app

# 复制 pom.xml 文件以利用 Docker 缓存层
COPY pom.xml .
COPY craft-domain/pom.xml ./craft-domain/
COPY craft-application/pom.xml ./craft-application/
COPY craft-infrastructure/pom.xml ./craft-infrastructure/
COPY craft-adaptor/pom.xml ./craft-adaptor/
COPY craft-start/pom.xml ./craft-start/

# 下载依赖（利用缓存）
RUN mvn dependency:go-offline -B

# 复制源代码
COPY craft-domain/src ./craft-domain/src
COPY craft-application/src ./craft-application/src
COPY craft-infrastructure/src ./craft-infrastructure/src
COPY craft-adaptor/src ./craft-adaptor/src
COPY craft-start/src ./craft-start/src

# 构建应用
RUN mvn clean package -DskipTests -B

# =============================================
# 运行时镜像
# =============================================
FROM openjdk:21-jdk-slim

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建应用用户（安全最佳实践）
RUN groupadd -r craft && useradd -r -g craft craft

# 设置工作目录
WORKDIR /app

# 从构建阶段复制 JAR 文件
COPY --from=builder /app/craft-start/target/craft-start-*.jar app.jar

# 更改文件所有者
RUN chown -R craft:craft /app

# 切换到非 root 用户
USER craft

# 暴露端口
EXPOSE 8080

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# JVM 参数优化
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]