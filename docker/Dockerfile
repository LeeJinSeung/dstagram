FROM centos:7

# Install basic packages
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG kr_KO.UTF-8

RUN yum -y update
RUN rpm -Uvh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
RUN yum install -y nginx wget telnet nc tar vim unzip net-tools java-11-openjdk-devel

# Dstagram setup
RUN mkdir -p /dstagram/operation
VOLUME ["/dstagram/operation"]
EXPOSE 80 8080
WORKDIR /dstagram
ADD run.sh /dstagram/run.sh
RUN chmod 755 /dstagram/run.sh
ADD nginx/nginx.conf /etc/nginx/nginx.conf

# ADD ssl/* /etc/nginx/
ADD war/*.war /dstagram/

ENTRYPOINT ["/dstagram/run.sh"]