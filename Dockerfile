FROM amazoncorretto:11.0.18
WORKDIR /tmp
COPY . .
RUN yum update -y && yum install -y make
RUN make
CMD ["java","ALGs.ALG_Tester"]