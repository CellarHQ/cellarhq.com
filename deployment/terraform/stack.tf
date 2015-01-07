# CellarHQ VPC Stack
#
# Barebones design; I've included some docs on the subnet
# topology I prefer, but we aren't implementing that intense.
# It's just for reference since I can never remember.
#
# VPC Subnet Design
#
# x.x.0.0/16 - VPC
#   x.x.0.0/18 - Zone0
#     x.x.0.0/19 - Private
#     x.x.32.0/19
#       x.x.32.0/20 - Public
#       x.x.48.0/20 - Spare
#   x.x.64.0/18 - Zone1
#     x.x.64.0/19 - Private
#     x.x.96.0/19
#       x.x.96.0/20 - Public
#       x.x.112.0/20 - Spare
#   x.x.128.0/18 - Zone2
#     x.x.128.0/19 - Private
#     x.x.160.0/19
#       x.x.160.0/20 - Public
#       x.x.176.0/20 - Spare
#   x.x.192.0/18 - Spare


provider "aws" {
    access_key = "${var.aws_access_key}"
    secret_key = "${var.aws_secret_key}"
    region = "us-east-1"
}

resource "aws_vpc" "main" {
    cidr_block = "10.0.0.0/16"
}

resource "aws_internet_gateway" "default" {
    vpc_id = "${aws_vpc.main.id}"
}

resource "aws_subnet" "us-east-1b-public" {
    vpc_id = "${aws_vpc.main.id}"

    cidr_block = "10.0.32.0/20"
    availability_zone = "us-east-1b"
}

resource "aws_route_table "default-public" {
    vpc_id = "${aws_vpc.main.id}"

    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = "${aws_internet_gateway.default.id}"
    }
}

resource "aws_route_table_association" "us-east-1b-public" {
    subnet_id = "${aws_subnet.us-east-1b-public.id}"
    route_table_id = "${aws_route_table.default-public.id}"
}

resource "aws_security_group" "web" {
    name = "web"
    description = "Allows web & SSH access to the web server"

    vpc_id = "${aws_vpc.main.id}"

    ingress {
        protocol = "tcp"
        from_port = 22
        to_port = 22
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        protocol = "udp"
        from_port = 53
        to_port = 53
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        protocol = "udp"
        from_port = 123
        to_port = 123
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        protocol = "icmp"
        from_port = -1
        to_port = -1
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        protocol = "tcp"
        from_port = 80
        to_port = 80
        cidr_blocks = ["0.0.0.0/0"]
    }
    ingress {
        protocol = "tcp"
        from_port = 443
        to_port = 443
        cidr_blocks = ["0.0.0.0/0"]
    }
}

resource "aws_elb" "web" {
    name = "web-elb"
    availability_zones = ["us-east-1b"]

    listener {
        lb_protocol = "https"
        lb_port = 443
        instance_protocol = "http"
        instance_port = 5050
        ssl_certificate_id = "arn:aws:iam::837820220306:server-certificate/www_cellarhq_com"
    }
    listener {
        lb_protocol = "http"
        lb_port = 80
        instance_protocol = "http"
        instance_port = 5050
    }

    health_check {
        target = "HTTP:5050/health-checks"
        healthy_threshold = 2
        unhealthy_threshold = 5
        timeout = 5
        interval = 30
    }

    cross_zone_load_balancing = true
    security_groups = ["${aws_security_group.web.id}"]
}

resource "aws_launch_configuration" "web" {
    name = "web_config"

    # TODO Audit that this AMI is actually any good.
    image_id = "ami-901ba3f8"
    instance_type = "t2.medium"
    key_name = "cellarhq-web"
    security_groups = ["${aws_security_group.web.id}"]

    # TODO User Data
}

resource "aws_autoscaling_group" "web" {
    name = "web_asg"
    availability_zones = ["us-east-1b"]
    min_size = 1
    max_size = 4
    desired_capacity = 1
    health_check_grace_period = 300
    health_check_type = "ELB"
    force_delete = true
    launch_configuration = "${aws_launch_configuration.web.name}"
    load_balancers = ["${aws_elb.web.id}"]
    vpc_zone_identifier = ["${aws_subnet.us-east-1b.id}"]
}

# TODO: Add RDS
