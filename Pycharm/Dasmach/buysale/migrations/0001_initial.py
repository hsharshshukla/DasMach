# -*- coding: utf-8 -*-
# Generated by Django 1.10.1 on 2018-03-07 18:50
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='car',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('owner_name', models.CharField(max_length=50)),
                ('owner_email', models.EmailField(max_length=200)),
                ('owner_mobile', models.CharField(max_length=10)),
                ('buy_year', models.CharField(choices=[('', 'Year'), ('1', '2018'), ('2', '2017'), ('3', '2016'), ('4', '2015'), ('5', '2014')], max_length=200)),
                ('veh_make_month', models.CharField(choices=[('', 'Month'), ('\t1\t', '\tJan\t'), ('\t2\t', '\tFeb\t'), ('\t3\t', '\tMar\t'), ('\t4\t', '\tApr\t'), ('\t5\t', '\tMay\t'), ('\t6\t', '\tJun\t'), ('\t7\t', '\tJul\t'), ('\t8\t', '\tAug\t'), ('\t9\t', '\tSep\t'), ('\t10\t', '\tOct\t'), ('\t11\t', '\tNov\t'), ('\t12\t', '\tDec\t')], max_length=200)),
                ('veh_owner', models.CharField(choices=[('', 'Owner'), ('1', 'First'), ('2', 'Second'), ('3', 'Third'), ('4', 'Company Owned')], max_length=200)),
                ('veh_make', models.CharField(choices=[('', 'Brand'), ('1', 'Mercedes'), ('2', 'Audi'), ('3', 'BMW'), ('4', 'MiNi'), ('5', 'Volvo'), ('6', 'Roll Royce'), ('7', 'Lamborgini'), ('8', 'Ferrari'), ('9', 'Bugatti'), ('10', 'Aston Martin'), ('11', 'Land Rover'), ('12', 'Jaguar'), ('13', 'Porsche'), ('14', 'Maserati'), ('15', 'Bentley')], max_length=200)),
                ('veh_km', models.CharField(choices=[('', 'Km'), ('1', 'Below 10000'), ('2', '10000-25000'), ('3', '25000+')], max_length=200)),
                ('veh_variant', models.CharField(choices=[('', 'Variant'), ('1', 'Top-Diesel'), ('2', 'Top-Petrol'), ('3', 'Mi-Diesel'), ('4', 'Mi-Petrol'), ('5', 'Low-Diesel'), ('6', 'Low-Petrol'), ('7', 'Hybrid')], max_length=200)),
                ('veh_Color', models.CharField(choices=[('', 'Color'), ('1', 'BEIG'), ('2', 'BLACK'), ('3', 'BLUE'), ('4', 'BRONZE'), ('5', 'BROWN'), ('6', 'GOLD'), ('7', 'GREEN'), ('8', 'GREY'), ('9', 'MAROON'), ('10', 'ORANGE'), ('11', 'PURPLE'), ('12', 'RED'), ('13', 'SILVER'), ('14', 'WHITE'), ('15', 'YELLOW')], max_length=200)),
                ('veh_fuel', models.CharField(choices=[('', 'Fuel'), ('1', 'Petrol'), ('2', 'Diesel'), ('3', 'Hybrid'), ('4', 'Electric'), ('5', 'Hydrogen')], max_length=200)),
                ('veh_usetype', models.CharField(choices=[('', 'Use'), ('1', 'Personal'), ('2', 'Commercial')], max_length=200)),
                ('veh_Insurancetype', models.CharField(choices=[('1', 'Comprehensive'), ('2', 'Third Party'), ('3', 'Expired')], default='Comprehensive', max_length=200)),
                ('veh_accident', models.CharField(choices=[('1', 'No'), ('2', 'Yes')], default='No', max_length=200)),
                ('veh_Insuranceclaimed', models.CharField(choices=[('1', 'No'), ('2', 'Yes')], default='No', max_length=200)),
                ('veh_Insuranceclaimamount', models.IntegerField(default=0)),
                ('veh_servicehistory', models.CharField(choices=[('1', 'No'), ('2', 'Yes')], default='No', max_length=200)),
                ('veh_existingLoan', models.CharField(choices=[('1', 'No'), ('2', 'Yes')], default='No', max_length=200)),
                ('veh_image1', models.FileField(blank=True, null=True, upload_to='photo')),
                ('veh_expectedprice', models.IntegerField(default=0)),
            ],
        ),
    ]
