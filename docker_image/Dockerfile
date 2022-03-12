# vim: filetype=dockerfile
# FROM debian:11
FROM ubuntu:20.04

# first dyalog apl and python stuff
RUN apt-get update && apt-get install -y python3 git curl libtinfo5
WORKDIR /root
RUN curl -O https://www.dyalog.com/uploads/php/download.dyalog.com/download.php?file=18.0/linux_64_18.0.40684_unicode.x86_64.deb
RUN git clone https://github.com/Dyalog/pynapl.git && cd pynapl && git checkout 8b17bceda64b182cf89f4c9b7b77580ec9daf2ed
ADD patch /root/
RUN cd pynapl && patch -p1 < ../patch
#ADD ./linux_64_18.0.40684_unicode.x86_64.deb /root/
RUN dpkg -i linux_64_18.0.40684_unicode.x86_64.deb

# now clojure stuff
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends tzdata
# RUN apt-get install -y tzdata
RUN apt-get install -y leiningen
RUN git clone --depth=1 https://github.com/liquidz/vim-iced.git

# just for testing
RUN apt-get install -y python3-pip && pip3 install numpy

# TODO this isn't working as expected
RUN echo 'declare -x PYTHONPATH=/root/pynapl' >> /root/.bashrc

ENTRYPOINT []
WORKDIR /mnt
CMD /root/vim-iced/bin/iced repl
# ^ TODO allow lein repl


# docker run --net=host -v apl-clj-m2:/root/.m2 -v `pwd`:/mnt --rm -it imagehere
