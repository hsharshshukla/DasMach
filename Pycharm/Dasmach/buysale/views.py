from django.shortcuts import render, redirect
from django.core.mail import send_mail
from buysale.forms    import vehicle,newsletter,contact
from django.http import JsonResponse
from django.contrib import messages

# Create your views here.
def index(request):
   if request.method=="POST":
        form = contact(request.POST)
        if form.is_valid():
            contact_item = form.save()
            contact_item.save()
   else:
        form = contact()
   return render(request, 'index.html', {'form':form})


def vup(request):
    if request.method == "POST":
        form = vehicle(request.POST, request.FILES)
        if form.is_valid():
            vehicle_item = form.save()
            vehicle_item.save()

    else:

        form = vehicle()
    return render(request, 'vehupl.html', {'form': form})


def catalogue(request):
    from buysale.models import car
    data = car.objects.all()
    car = {
        "vehicle": data
    }
    if request.method == "POST":
        form = newsletter(request.POST)
        if form.is_valid():
            newsletter_item = form.save()
            newsletter_item.save()
    else:
        form = newsletter()
    return render(request, 'products.html', car)


def single(request):
    return render(request, 'single.html')
